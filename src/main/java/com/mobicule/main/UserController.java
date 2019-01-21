package com.mobicule.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobicule.utilities.ImageManipulation;
import com.mobicule.utilities.ProducerUserBean;





//./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic KafkaProducerConsumerExample
@RestController
@RequestMapping("/kafka")
public class UserController {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/publish")
	public String post() //@PathVariable("message") final String message
	{
		String message = "I am Ajinkya and currently working in Mobicule";
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("Ajinkya",message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> arg0) {
				// TODO Auto-generated method stub
				
				System.out.println("Topic ==> "+arg0.getRecordMetadata().topic());
				System.out.println("Partition ==> "+arg0.getRecordMetadata().partition());
				System.out.println("Offset ==> "+arg0.getRecordMetadata().offset());
				
			}

		    @Override
		    public void onFailure(Throwable ex) {
		        System.out.println("Exception  ==> "+ex.getMessage());
		    }

			
		});
		ImageManipulation.display();
		System.out.println("Published Successfully");
		return "Published Successfully";
		
	}
	
	
	
	
	@PostMapping("/send")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ProducerUserBean> insertData(@RequestBody Map<String,List<ProducerUserBean>>userMap)
	{
		List<ProducerUserBean> userList = userMap.get("userData");
		System.out.println(userList.size());
		String imageStoragePath="";
		for(ProducerUserBean ub : userList)
		{
			//Decoding imageString to byteArray
			byte[] imageDataArray = ImageManipulation.decodeImageString(ub.getImageString());
			
			try 
			{
				imageStoragePath = "/home/ajinkya/Pictures/Java-Image-Write/"+"csvImport"+ub.getId()+".png";
				FileOutputStream imageOutFile = new FileOutputStream(new File(imageStoragePath));
				imageOutFile.write(imageDataArray);
				imageOutFile.close();
				
				
				ub.setImageLocation(imageStoragePath);
				sendProducerMessage(ub);
				
			} 
			
			
			
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return userList;
	}
	
	public void sendProducerMessage(ProducerUserBean bean)

	{
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody="";
		try 
		{
			 jsonBody = mapper.writeValueAsString(bean);
			System.out.println("String value :: "+jsonBody);
		}
		catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("KafkaProducerConsumerExample",jsonBody);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> arg0) {
				// TODO Auto-generated method stub
				
				System.out.println("Topic ==> "+arg0.getRecordMetadata().topic());
				System.out.println("Partition ==> "+arg0.getRecordMetadata().partition());
				System.out.println("Offset ==> "+arg0.getRecordMetadata().offset());
				
			}

		    @Override
		    public void onFailure(Throwable ex) {
		        System.out.println("Exception  ==> "+ex.getMessage());
		    }

			
		});
	}

	
	public void consumeMessages()
	{
		System.out.println();
	}
	
}
