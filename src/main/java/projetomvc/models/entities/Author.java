package projetomvc.models.entities;

public class Author {
  private String name;
  private String hometown;
  private Integer birthDate;

  public Author() {
    this.name = "";
    this.hometown = "";
    this.birthDate = null;
  }

  public Author(String name, String hometown, Integer birthDate) {
    this.name = name;
    this.hometown = hometown;
    this.birthDate = birthDate;
  }
  
  public Author(Author other) {
    this.name = other.getName();
    this.hometown = other.getHometown();
    this.birthDate = other.getBirthDate();
  }

  public void copy(Author other) {
    this.name = other.getName();
    this.hometown = other.getHometown();
    this.birthDate = other.getBirthDate();
  }
  
  @Override
  public String toString() {
    return "Autor [name=" + name + ", hometown=" + hometown + ", birthDate=" + birthDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((hometown == null) ? 0 : hometown.hashCode());
    result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
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
    Author other = (Author) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (hometown == null) {
      if (other.hometown != null)
        return false;
    } else if (!hometown.equals(other.hometown))
      return false;
    if (birthDate == null) {
      if (other.birthDate != null)
        return false;
    } else if (!birthDate.equals(other.birthDate))
      return false;
    return true;
  }

  public String getName() {
    return name;
  }

  public String getHometown() {
    return hometown;
  }

  public Integer getBirthDate() {
    return birthDate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setHometown(String hometown) {
    this.hometown = hometown;
  }

  public void setBirthDate(Integer birthDate) {
    this.birthDate = birthDate;
  }
}
