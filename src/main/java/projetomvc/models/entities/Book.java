package projetomvc.models.entities;

public class Book {
  private Integer id;
  private String title;
  private Integer authorId;
  private Integer publishedYear;

  public Book() {
    this.id = null;
    this.title = "";
    this.authorId = null;
    this.publishedYear = null;
  }

  public Book(Integer id, String title, Integer authorId, Integer publishedYear) {
    this.id = id;
    this.title = title;
    this.authorId = authorId;
    this.publishedYear = publishedYear;
  }

  public Book(Book other) {
    this.id = other.getId();
    this.title = other.getTitle();
    this.authorId = other.getAuthorId();
    this.publishedYear = other.getPublishedYear();
  }

  public void copy(Book other) {
    this.id = other.getId();
    this.title = other.getTitle();
    this.authorId = other.getAuthorId();
    this.publishedYear = other.getPublishedYear();
  }

  @Override
  public String toString() {
    return "Livro [id=" + id + ", title=" + title + ", authorId=" + authorId + ", publishedYear=" + publishedYear + "]";
  }

  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
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
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (authorId == null) {
      if (other.authorId != null)
        return false;
    } else if (!authorId.equals(other.authorId))
      return false;
    if (publishedYear == null) {
      if (other.publishedYear != null)
        return false;
    } else if (!publishedYear.equals(other.publishedYear))
      return false;
    return true;
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
  
  public Integer getAuthorId() {
    return authorId;
  }

  public Integer getPublishedYear() {
    return publishedYear;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public void setPublishedYear(Integer publishedYear) {
    this.publishedYear = publishedYear;
  }
}
