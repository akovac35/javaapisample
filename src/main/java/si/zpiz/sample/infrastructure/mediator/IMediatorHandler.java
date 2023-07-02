package si.zpiz.sample.infrastructure.mediator;

import si.zpiz.sample.infrastructure.exceptions.MediatorException;

/**
 * Handlers must be singleton Services.
 */
public interface IMediatorHandler<TRequest extends IMediatorRequest<TResponse>, TResponse> {
    public TResponse handle(TRequest request) throws MediatorException;
}
