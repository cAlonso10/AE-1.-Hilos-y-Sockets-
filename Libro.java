
public class Libro {
    private String titulo;
    private String autor;
    private int precio;
    private int isbn;

    public Libro(String titulo, String autor, int precio, int isbn) {
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
    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "libros [titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", isbn=" + isbn + "]";
    }

    
}