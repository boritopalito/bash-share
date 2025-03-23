package nl.xx1.share;

import org.springframework.boot.SpringApplication;

public class TestShareApplication {

	public static void main(String[] args) {
		SpringApplication.from(ShareApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
