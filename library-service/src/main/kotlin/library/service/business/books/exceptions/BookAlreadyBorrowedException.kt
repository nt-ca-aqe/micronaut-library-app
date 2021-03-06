package library.service.business.books.exceptions

import library.service.business.books.domain.types.BookId
import library.service.business.exceptions.NotPossibleException

class BookAlreadyBorrowedException(id: BookId)
    : NotPossibleException("The book with ID: $id is already borrowed!")

