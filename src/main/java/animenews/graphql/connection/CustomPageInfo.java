package animenews.graphql.connection;

import graphql.relay.PageInfo;


public interface CustomPageInfo extends PageInfo {
    int getTotalPages();
    int getPage();
    int getPerPage();
    long getTotalElements();
}
