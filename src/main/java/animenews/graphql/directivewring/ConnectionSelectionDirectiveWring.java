package animenews.graphql.directivewring;

import animenews.graphql.queryMutation.ConnectionQuery;
import graphql.language.StringValue;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

public class ConnectionSelectionDirectiveWring implements SchemaDirectiveWiring {
    private final ConnectionQuery connectionQuery;

    public ConnectionSelectionDirectiveWring(ConnectionQuery connectionQuery) {
        this.connectionQuery = connectionQuery;
    }

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> env) {
        String targetConnection = ((StringValue) env.getDirectives().get("ConnectionSelect").getArgument("target").getArgumentValue().getValue()).getValue();
        GraphQLFieldDefinition field = env.getElement();
        GraphQLFieldsContainer parentType = env.getFieldsContainer();
        DataFetcher select = null;
//        switch (targetConnection) {
//            case "StaffConnection":
//                select = (env1) -> this.connectionQuery.getStaffConnection(env1);
//                break;
//            case "StudioConnection":
//                select = (env1) -> this.connectionQuery.getStudioConnection(env1);
//                break;
//            case "CharacterConnection":
//                select = (env1) -> this.connectionQuery.getCharacterConnection(env1);
//                break;
//            case "VideoConnection":
//                select = (env1) -> this.connectionQuery.getVideoConnection(env1);
//                break;
//            case "CategoryConnection":
//                select = (env1) -> this.connectionQuery.getCategoryConnection(env1);
//                break;
//            case "TagConnection":
//                select = (env1) -> this.connectionQuery.getTagConnection(env1);
//                break;
//            default:
//                break;
//        }
        env.getCodeRegistry().dataFetcher(parentType, field, select);
        return field;
    }

}
