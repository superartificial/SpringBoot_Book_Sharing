import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { Router } from '@angular/router';
import { BookResponse, PageResponseBookResponse } from '../../../../services/models';
import { NgIf, NgFor } from '@angular/common';
import { BookCardComponent } from '../../components/book-card/book-card.component';


@Component({
  selector: 'app-book-list',
  imports: [NgIf, NgFor, BookCardComponent],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit {

  bookResponse: PageResponseBookResponse = {};
  page: number = 0;
  pageSize: number = 5;
  message: string = '';
  level: string = 'success';

  constructor(
    private bookService: BookService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.findAllBooks();
  }

  findAllBooks() {
    this.bookService.findAllBooks({
      page: this.page,
      size: this.pageSize
    }).subscribe({
      next: (books: PageResponseBookResponse) => {
        this.bookResponse = books;
      }
    })
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBooks();
  }

  goToPreviousPage() {
    if (this.page <= 0) return;
    this.page--;
    this.findAllBooks();
  }

  goToPage(index: number) {
    this.page = index;
    this.findAllBooks();
  }

  goToNextPage() {
    if (this.page >= (this.bookResponse.totalPages as number - 1)) return;
    this.page++;
    this.findAllBooks();
  }

  goToLastPage() {
    if (this.page >= (this.bookResponse.totalPages as number - 1)) return;
    this.page = this.bookResponse.totalPages as number - 1;
    this.findAllBooks();
  }

  get isLastPage(): boolean {
    return this.page == this.bookResponse.totalPages as number - 1;
  }

  borrowBook(book: BookResponse) {
    this.message = '';
    this.bookService.borrowBook({
      'book-id': book.id as number
    }).subscribe({
      next: () => {
        this.level = 'success';
        this.message = 'Book successfully borrowed.';
      },
      error: (err) => {
        this.level = 'error';
        this.message = err.error.error;
      }
    });
  }

}
