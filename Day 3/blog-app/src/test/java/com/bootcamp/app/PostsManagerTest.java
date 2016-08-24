package com.bootcamp.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostsManagerTest {
	
	User matias = new User("matias", "matias@gmail.com");
	User federico = new User("federico", "federico@gmail.com");
	User alejo = new User("alejo", "alejo@gmail.com");
	
	Post matiasPost1;
	Post federicosPost1;
	Post alejosPost2;
	List<Post> sportsPosts = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		
		Set<String> tags = new HashSet<>();
		tags.add("sports");
		tags.add("cars");
		tags.add("books");
		TagsManager.getInstance().addTags(tags);
		
		Set<String> sportsTags = new HashSet<>();
		sportsTags.add("sports");
		
		Set<String> someTags = new HashSet<>();
		someTags.add("cars");
		someTags.add("books");
		
		matiasPost1 = PostsManager.getInstance().createPost("matiasPost1", "matiasText1", tags, matias);
		PostsManager.getInstance().createPost("matiasPost2", "matiasText2", someTags, matias);
		PostsManager.getInstance().createPost("matiasPost3", "matiasText3", null, matias);
		federicosPost1 = PostsManager.getInstance().createPost("federicosPost1", "federicosText1", tags, federico);
		PostsManager.getInstance().createPost("federicosPost2", "federicosText2", someTags, federico);
		PostsManager.getInstance().createPost("federicosPost3", "federicosText3", someTags, federico);
		PostsManager.getInstance().createPost("alejosPost1", "alejosText1", null, alejo);
		alejosPost2 = PostsManager.getInstance().createPost("alejosPost2", "alejosText2", sportsTags, alejo);
		PostsManager.getInstance().createPost("alejosPost3", "alejosText3", someTags, alejo);
		
		sportsPosts.add(matiasPost1);
		sportsPosts.add(federicosPost1);
		sportsPosts.add(alejosPost2);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPostWasDeleted() {
		PostsManager.getInstance().deletePost(matiasPost1);
		assertTrue(!PostsManager.getInstance().getPosts().contains(matiasPost1));
	}

	@Test
	public void testSearchByTagReturnsPostsWithThatTag() {
		List<Post> posts = PostsManager.getInstance().searchByTag("sports");
		assertTrue(sportsPosts.containsAll(posts));
	}
}
