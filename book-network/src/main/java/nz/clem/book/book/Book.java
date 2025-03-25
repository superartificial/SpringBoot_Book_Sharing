package nz.clem.book.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nz.clem.book.common.BaseEntity;
import nz.clem.book.feedback.Feedback;
import nz.clem.book.history.BookTransactionHistory;
import nz.clem.book.user.User;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {
    private String title;
    private String authorName;
    private String isnb;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean sharable;

    @ManyToOne
    @JoinColumn(name = "owner_id") // optional, Hibernate will use this name by default
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(feedback -> feedback.getNote() != null ? feedback.getNote() : 0.0)
                .average()
                .orElse(0.0);
        double roundedRate = Math.round(rate * 10.0) / 10.0;
        return roundedRate;
    }

}
