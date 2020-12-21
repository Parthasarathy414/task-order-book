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

import com.task.model.Channel;
import com.task.repository.ChannelRepository;

@RestController
@RequestMapping("/channel")
public class ChannelController {


	private final ChannelRepository channelRepository;

	@Autowired
	public ChannelController(ChannelRepository channelRepository) {
		this.channelRepository=channelRepository;
	}


	@PostMapping("/save")
	public Channel save(@RequestBody Channel channelEntity) {
		return channelRepository.save(channelEntity);
	}

	@GetMapping("/getAll")
	public List<Channel> listAll(){
		return channelRepository.findAll();
	}

	@GetMapping(path= {"/{id}"})
	public ResponseEntity<Channel> findById(@PathVariable long id){
		return channelRepository.findById(id)
				.map(record-> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value="/{id}")
	public ResponseEntity<Channel> update(@PathVariable("id") long id, @RequestBody Channel channel){

		return channelRepository.findById(id).map(record->{
			record.setChan_name(channel.getChan_name());
			record.setSubchan_name(channel.getSubchan_name());
			record.setType(channel.getType());
			record.setData(channel.getData());
			Channel updated=channelRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id){
		return channelRepository.findById(id)
				.map(record->{
					channelRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}	
}
