package me.beldon;

import me.beldon.module.demo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateApplicationTests {

	@Test
	public void contextLoads() {

		User user = new User() {
			public void setAge(Integer age) {
				super.setAge(age);
			}
		};
		Runnable runnable = new Runnable() {
			@Override
			public void run() {

			}
		};
	}

}
