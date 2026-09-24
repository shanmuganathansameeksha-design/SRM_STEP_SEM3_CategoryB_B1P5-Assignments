public abstract class ArtPiece {
    private static int counter = 1001;
    private final String pieceId;

    public ArtPiece() {
        this.pieceId = "ART-" + counter++;
    }

    public String getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {
    private String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}