package animenews.graphql;

import animenews.graphql.directivewring.ConnectionSelectionDirectiveWring;
import animenews.graphql.resolvers.objectResolvers.ConnectionQuery;
import animenews.graphql.scalar.ScalarTypeUtils;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.kickstart.autoconfigure.tools.SchemaDirective;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GraphqlConfiguration {

    private final ConnectionQuery connectionQuery;

    public GraphqlConfiguration(ConnectionQuery connectionQuery) {
        this.connectionQuery = connectionQuery;
    }

    @Bean
    public SchemaDirective connectionSelectSchemaDirective() {
        return new SchemaDirective(
                "ConnectionSelect",
                new ConnectionSelectionDirectiveWring(connectionQuery)
        );
    }

    @Bean
    public Instrumentation instrumentationState() {
        return new TracingInstrumentation();
    }

    @Bean
    public GraphQLScalarType longScalar() {
        return ScalarTypeUtils.longScalar();
    }

    @Bean
    public GraphQLScalarType dateScalar() {
        return ScalarTypeUtils.dateScalar();
    }

}
