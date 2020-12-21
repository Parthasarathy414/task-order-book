package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long>{

}
