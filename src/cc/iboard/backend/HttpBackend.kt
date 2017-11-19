/**
The HTTP-Backend is responsible to extract the request from
the http-input, create a Endpoint and execute it.

We trust in com.sun.net.httpserver and do not test this simple
responsibility inside this class. Just because it's to hard
and all the methods we call from this application are tested anyways.

 */
package cc.iboard.backend

import java.io.IOException
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.URI
import java.util.logging.Level
import java.util.logging.Logger

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer


// IGNORE COVERAGE IN THIS FILE - See description above


/**
 * The `HttpBackend` starts a simple http-server and
 * a handler for path /all
 * the path is passed to `Responder.handle(path)`
 *
 * The server will not be stopped and blocks until
 * Ctrl-C is pressed by the user or the process is killed.
 */

class HttpBackend(port:Int) : BackendInterface {

   private var port = 8000
   private val logger = Logger.getLogger(this.javaClass.name)

   init{
      this.port = port
      createHttpServer()
   }


// PUBLIC API

   override fun start() {
      startHttpServer()
   }

   override fun request(_method:String, requestString:String):Response {
      return directCallWarning(requestString)
   }

   override fun stop() {
      server!!.stop(0)
   }

   private fun createHttpServer() {
      try
      {
         server = HttpServer.create(InetSocketAddress(port), 0)
      }
      catch (e:IOException) {
         println("Server can't be started. Error: " + e.message)
      }

   }

   private fun startHttpServer() {
      server!!.createContext("/", RootHandler())
      server!!.start()
   }

   private fun directCallWarning(requestString:String):Response {
      val msg = "Backend Handle was called directly in HttpBackend with: " + requestString
      logger.log(Level.WARNING, msg)
      return Response(Response.HTTP_FORBIDDEN, msg)
   }

   internal class RootHandler:HttpHandler {

      @Throws(IOException::class)
      override fun handle(t:HttpExchange) {
         val response = serve(extractMethod(t), getQueryString(t))
         sendResponse(t, response)
      }

      private fun serve(method:String, path:String)
              :Response = requester.request(method, path)

   }

   companion object {
      private var server:HttpServer? = null
      private val requester = Requester()



// IMPLEMENTATION

      @Throws(IOException::class)
      private fun sendResponse(t:HttpExchange, response:Response) {
         t.sendResponseHeaders(response.status(), response.body().length.toLong())
         val os = t.responseBody
         os.write(response.body().toByteArray())
         os.close()
      }

      private fun getQueryString(t:HttpExchange):String {
         val path = extractPathWithQuery(t)
         return if (isRootPath(path))
            "Index"
         else
            BackendHelper.upcaseFirstChar(path)
      }

      private fun isRootPath(path:String?):Boolean {
         return if (path == null)
            true
         else path == "/"
      }

      private fun extractMethod(t:HttpExchange)
              :String = t.requestMethod.toUpperCase()

      private fun extractPathWithQuery(t:HttpExchange):String {
         val uri = t.requestURI
         val query = uri.query
         return if (query == null)
            uri.path
         else
            uri.path + "?" + uri.query
      }
   }

}
