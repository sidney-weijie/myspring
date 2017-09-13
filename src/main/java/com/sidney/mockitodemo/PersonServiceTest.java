package com.sidney.mockitodemo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
	@Mock
	private PersonDao personDAO;
	private PersonService personService;
	
	public PersonServiceTest(){
		
	}
	
	@BeforeClass
	public static void setUpClass(){
		
	}
	
	@AfterClass
	public static void tearDownClass(){
		
	}
	
	//在@Test标注的测试方法之前运行
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		//用模拟对象创建被测类对象
		personService = new PersonService(personDAO);
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void shouldUpdatePersonName(){
		Person person = new Person(1, "test1");
		when(personDAO.fetchPerson(1)).thenReturn(person);
		
		boolean updated = personService.update(1, "David");
		Assert.assertTrue(updated);
		verify(personDAO).fetchPerson(1);
		//验证模拟对象的fetchPerson(1)是否被调用了一次
		ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
		//验证模拟对象的update()是否被调用了一次，并抓取调用时传入的参数值
		verify(personDAO).update(personCaptor.capture());
		
		//获取抓取到的参数值
		Person updaPerson = personCaptor.getValue();
		
		Assert.assertSame("David", updaPerson.getPersonName());
		//检查模拟对象上是否还有未验证的交互
		
		verifyNoMoreInteractions(personDAO);
	}
	
	
	@Test
	public void shouldNotUpdatePersonNotFound(){
		// 设置模拟对象的返回预期值  
        when(personDAO.fetchPerson(1)).thenReturn(null);  
     // 执行测试  
        boolean updated = personService.update(1, "David");  
        // 验证更新是否失败  
        Assert.assertFalse(updated);  
        // 验证模拟对象的fetchPerson(1)方法是否被调用了一次  
        verify(personDAO).fetchPerson(1);  
        // 验证模拟对象是否没有发生任何交互  
        verifyZeroInteractions(personDAO);  
        // 检查模拟对象上是否还有未验证的交互  
        verifyNoMoreInteractions(personDAO); 
	}
	
    @Test  
    public void testUpdate() {  
        System.out.println("update");  
        Integer personId = null;  
        String name = "Phillip";  
        PersonService instance = new PersonService(new PersonDao() {  
  
            public Person fetchPerson(Integer personID) {  
                System.out.println("Not supported yet.");  
                return null;  
            }  
  
            public void update(Person person) {  
                System.out.println("Not supported yet.");  
            }  
        });  
        boolean expResult = false;  
        boolean result = instance.update(personId, name);  
        Assert.assertEquals(expResult, result);  
        // TODO review the generated test code and remove the default call to fail.  
        fail("The test case is a prototype.");  
    }  
}
