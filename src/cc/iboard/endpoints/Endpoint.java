package cc.iboard.endpoints;

/**
 * Derive from class `Endpoint` to handle your requests.
 * Example:
 *
 * If you want to have an endpoint for
 * `http://localhost/MyRequest`, then derive a class
 * `MyRequest` from `Endpoint` and implement the `respond` function.
 *
 * @see Index
 * @see State
 */
public abstract class Endpoint {
    public abstract String respond();
}
