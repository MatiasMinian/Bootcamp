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
	
	Post matiasPost1, matiasPost2, matiasPost3;
	Post federicosPost1, federicosPost2, federicosPost3;
	Post alejosPost1, alejosPost2, alejosPost3;
	
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
		
		matiasPost1 = matias.createPost("matiasPost1", "matiasText1", tags);
		matiasPost2 = matias.createPost("matiasPost2", "matiasText2", someTags);
		matiasPost3 = matias.createPost("matiasPost3", "matiasText3", null);
		federicosPost1 = federico.createPost("federicosPost1", "federicosText1", tags);
		federicosPost2 = federico.createPost("federicosPost2", "federicosText2", someTags);
		federicosPost3 = federico.createPost("federicosPost3", "federicosText3", someTags);
		alejosPost1 = alejo.createPost("alejosPost1", "alejosText1", null);
		alejosPost2 = alejo.createPost("alejosPost2", "alejosText2", sportsTags);
		alejosPost3 = alejo.createPost("alejosPost3", "alejosText3", someTags);
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
	public void testSearchByTag() {
		List<Post> sportsPosts = new ArrayList<>();
		sportsPosts.add(matiasPost1);
		sportsPosts.add(federicosPost1);
		sportsPosts.add(alejosPost2);
		
		List<Post> posts = PostsManager.getInstance().searchByTag("sports");
		assertTrue(sportsPosts.containsAll(posts));
	}
	
	@Test
	public void testSortByLikes() {
		matiasPost2.addLike(alejo);
		federicosPost1.addLike(matias);
		federicosPost1.addLike(alejo);
		
		List<Post> expectedPosts = new ArrayList<>();
		expectedPosts.add(federicosPost1);
		expectedPosts.add(matiasPost2);
		expectedPosts.add(matiasPost1);		
		expectedPosts.add(matiasPost3);
		expectedPosts.add(federicosPost2);
		expectedPosts.add(federicosPost3);
		expectedPosts.add(alejosPost1);
		expectedPosts.add(alejosPost2);
		expectedPosts.add(alejosPost3);
		
		List<Post> posts = PostsManager.getInstance().sortByLikes(0, false);
		assertTrue(expectedPosts.equals(posts));
	}
	
	@Test
	public void testSortAlphabeticallyByTitle() {
		List<Post> expectedPosts = new ArrayList<>();
		expectedPosts.add(alejosPost1);
		expectedPosts.add(alejosPost2);
		expectedPosts.add(alejosPost3);
		expectedPosts.add(federicosPost1);
		expectedPosts.add(federicosPost2);
		expectedPosts.add(federicosPost3);
		expectedPosts.add(matiasPost1);
		expectedPosts.add(matiasPost2);
		expectedPosts.add(matiasPost3);
		
		List<Post> posts = PostsManager.getInstance().sortAlphabeticallyByTitle(0, false);
		assertTrue(expectedPosts.equals(posts));
	}
}
