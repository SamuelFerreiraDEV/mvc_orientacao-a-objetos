package projetomvc.models.entities;

public class Book {
  private String title;
  private Author author;
  private Integer publishedYear;

  public Book() {
    this.title = "";
    this.author = null;
    this.publishedYear = null;
  }

  public Book(String title, Author author, Integer publishedYear) {
    this.title = title;
    this.author = author;
    this.publishedYear = publishedYear;
  }

  public Book(Book other) {
    this.title = other.getTitle();
    this.author = other.getAuthor();
    this.publishedYear = other.getPublishedYear();
  }

  public void copy(Book other) {
    this.title = other.getTitle();
    this.author = other.getAuthor();
    this.publishedYear = other.getPublishedYear();
  }

  @Override
  public String toString() {
    return "Livro [title=" + title + ", author=" + author + ", publishedYear=" + publishedYear + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((publishedYear == null) ? 0 : publishedYear.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Book other = (Book) obj;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
      return false;
    if (publishedYear == null) {
      if (other.publishedYear != null)
        return false;
    } else if (!publishedYear.equals(other.publishedYear))
      return false;
    return true;
  }

  public String getTitle() {
    return title;
  }
  
  public Author getAuthor() {
    return author;
  }

  public Integer getPublishedYear() {
    return publishedYear;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setAuthor(Author author) {
    this.author = author;
  }

  public void setPublishedYear(Integer publishedYear) {
    this.publishedYear = publishedYear;
  }
}
