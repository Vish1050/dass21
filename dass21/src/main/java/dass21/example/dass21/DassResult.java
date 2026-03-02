package dass21.example.dass21;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DassResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private LocalDate testDate;
    private int depScore;
    private int anxScore;
    private int strScore;
    private String depLevel;
    private String anxLevel;
    private String strLevel;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public LocalDate getTestDate() { return testDate; }
    public void setTestDate(LocalDate testDate) { this.testDate = testDate; }
    public int getDepScore() { return depScore; }
    public void setDepScore(int depScore) { this.depScore = depScore; }
    public int getAnxScore() { return anxScore; }
    public void setAnxScore(int anxScore) { this.anxScore = anxScore; }
    public int getStrScore() { return strScore; }
    public void setStrScore(int strScore) { this.strScore = strScore; }
    public String getDepLevel() { return depLevel; }
    public void setDepLevel(String depLevel) { this.depLevel = depLevel; }
    public String getAnxLevel() { return anxLevel; }
    public void setAnxLevel(String anxLevel) { this.anxLevel = anxLevel; }
    public String getStrLevel() { return strLevel; }
    public void setStrLevel(String strLevel) { this.strLevel = strLevel; }
}