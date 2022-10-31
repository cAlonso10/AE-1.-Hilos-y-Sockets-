
public class Libro {
    private String titulo;
    private String autor;
    private int precio;
    private String isbn;


    public Libro(String titulo, String autor, int precio, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.isbn = isbn;
    }
    

    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Libro [Titulo:" + titulo + ", Autor:" + autor + ", Precio:" + precio + ", Isbn:" + isbn + "]";
    }

    
}