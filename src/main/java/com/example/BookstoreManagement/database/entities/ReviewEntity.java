package com.example.BookstoreManagement.database.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(nullable = false)
    private Integer rate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_bookId"))
    private BookEntity bookId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_userId"))
    private UserEntity userId;

    @Column
    private String content;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public ReviewEntity(Integer rate, BookEntity bookId, UserEntity userId, String content) {
        this.rate = rate;
        this.bookId = bookId;
        this.userId = userId;
        this.content = content;
    }
}
