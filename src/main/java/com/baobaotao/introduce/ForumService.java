package com.baobaotao.introduce;

public class ForumService {

	public void removeTopic(int topicId) {
		System.out.println("ÒÆ³ýTopic:"+topicId);
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		

	}

	public void removeForum(int forumId) {
		System.out.println("ÒÆ³ýForum:"+forumId);
		try {
			Thread.currentThread().sleep(40);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
}
