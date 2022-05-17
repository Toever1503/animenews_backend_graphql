package animenews.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class ScalarTypeUtils {
    public static GraphQLScalarType longScalar() {
        return GraphQLScalarType.newScalar()
                .name("Long")
                .description("Long scalar type")
                .coercing(new Coercing() {
                    @Override
                    public Object serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                        return dataFetcherResult;
                    }

                    @NotNull
                    @Override
                    public Object parseValue(@NotNull Object input) throws CoercingParseValueException {
                        return null;
                    }

                    @NotNull
                    @Override
                    public Object parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                        return null;
                    }
                })
                .build();
    }

    public static GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .coercing(new Coercing() {
                    @Override
                    public Object serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                        return dataFetcherResult;
                    }

                    @NotNull
                    @Override
                    public Object parseValue(@NotNull Object input) throws CoercingParseValueException {
                        String date = ((StringValue) input).getValue(); // format and convert later
                        try {
                            return new Date(date);
                        } catch (Exception e) {
                            throw new CoercingParseLiteralException("Could not parse date: " + date);
                        }
                    }

                    @NotNull
                    @Override
                    public Object parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                        String date = ((StringValue) input).getValue();  // format and convert later
                        try {
                            return new Date(date);
                        } catch (Exception e) {
                            throw new CoercingParseLiteralException("Could not parse date: " + date);
                        }
                    }
                })
                .build();
    }

}
