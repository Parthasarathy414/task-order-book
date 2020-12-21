package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Book;
import com.task.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {

	private final BookRepository bookRepository;

	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository=bookRepository;
	}


	@PostMapping("/save")
	public Book saveBook(@RequestBody Book bookEntity) {
		return bookRepository.save(bookEntity);
	}

	@GetMapping("/getAll")
	public List<Book> listAll(){
		return bookRepository.findAll();
	}

	@GetMapping(path= {"/{id}"})
	public ResponseEntity<Book> findById(@PathVariable long id){
		return bookRepository.findById(id)
				.map(record-> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value="/{id}")
	public ResponseEntity<Book> update(@PathVariable("id") long id, @RequestBody Book book){

		return bookRepository.findById(id).map(record->{
			record.setMarket(book.getMarket());
			record.setPrice(book.getPrice());
			record.setSide(book.getSide());
			record.setSize(book.getSize());
			record.setChannel(book.getChannel());
			Book updated=bookRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id){
		return bookRepository.findById(id)
				.map(record->{
					bookRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}	
}
