package animenews.graphql.exception;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class CustomDataFetcherExceptionHandler implements DataFetcherExceptionHandler {

    public CustomDataFetcherExceptionHandler (){
        System.out.println("CustomDataFetcherExceptionHandler");
    }

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        return null;
    }

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        return DataFetcherExceptionHandler.super.handleException(handlerParameters);
    }
}
