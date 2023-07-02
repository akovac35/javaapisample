package si.zpiz.sample.infrastructure.mediator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import si.zpiz.sample.infrastructure.exceptions.MediatorException;

@Service
@Slf4j
public class Mediator {

        private ApplicationContext applicationContext;
        private ConcurrentHashMap<Class<?>, IMediatorHandler<?, ?>> handlerCache = new ConcurrentHashMap<>();

        public Mediator(ApplicationContext applicationContext) {
                this.applicationContext = applicationContext;
        }

        @SuppressWarnings("all")
        public <TRequest extends IMediatorRequest<TResponse>, TResponse> TResponse send(
                        IMediatorRequest<TResponse> request) throws MediatorException {

                Object bean = handlerCache.get(request.getClass());

                if (bean == null) {
                        Map<String, IMediatorHandler> beans = applicationContext.getBeansOfType(IMediatorHandler.class);
                        for (IMediatorHandler handler : beans.values()) {
                                Method[] methods = handler.getClass().getMethods();
                                for (Method method : methods) {
                                        if (method.getName().equals("handle")
                                                        && method.getParameterTypes()[0].equals(request.getClass())) {
                                                handlerCache.put(request.getClass(), handler);
                                                bean = handler;
                                        }
                                }
                        }
                }

                if (bean != null) {
                        return handleSend(request,
                                        (IMediatorHandler<IMediatorRequest<TResponse>, TResponse>) bean);
                }

                throw new MediatorException("Handler not found for request: " + request.getClass().getName());
        }

        private <TResponse> TResponse handleSend(IMediatorRequest<TResponse> request,
                        IMediatorHandler<IMediatorRequest<TResponse>, TResponse> handler)
                        throws MediatorException {
                if (log.isDebugEnabled()) {
                        log.debug("Handling request: {} {}", request.getClass().getName(), request.toString());
                }

                TResponse response = handler.handle(request);

                if (log.isDebugEnabled()) {
                        if (response != null) {
                                log.debug("Request handled: {} {}", response.getClass().getName(), response.toString());
                        } else {
                                log.debug("Request handled: null");
                        }
                }

                return response;
        }
}
