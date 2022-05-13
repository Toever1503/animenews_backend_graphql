package animenews.graphql.connection;

import graphql.relay.ConnectionCursor;


public class DefaultCustomPageInfo implements CustomPageInfo {
    private final boolean hasNextPage;
    private final boolean hasPreviousPage;
    private final int totalPages;
    private final int page;
    private final int perPage;
    private final long totalElements;

    public DefaultCustomPageInfo(boolean hasNextPage, boolean hasPreviousPage, int totalPages, int page, int perPage, long totalElements) {
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.totalPages = totalPages;
        this.page = page;
        this.perPage = perPage;
        this.totalElements = totalElements;
    }

    @Override
    public ConnectionCursor getStartCursor() {
        return null;
    }

    @Override
    public ConnectionCursor getEndCursor() {
        return null;
    }

    @Override
    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    @Override
    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    @Override
    public int getTotalPages() {
        return this.totalPages;
    }

    @Override
    public int getPage() {
        return this.page;
    }

    @Override
    public int getPerPage() {
        return this.perPage;
    }

    @Override
    public long getTotalElements() {
        return this.totalElements;
    }
}
