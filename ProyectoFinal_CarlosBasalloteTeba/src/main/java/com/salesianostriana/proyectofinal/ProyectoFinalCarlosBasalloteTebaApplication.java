package com.salesianostriana.proyectofinal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.salesianostriana.proyectofinal.model.Ong;
import com.salesianostriana.proyectofinal.model.Voluntario;
import com.salesianostriana.proyectofinal.service.OngService;
import com.salesianostriana.proyectofinal.service.VoluntarioService;

@SpringBootApplication
public class ProyectoFinalCarlosBasalloteTebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalCarlosBasalloteTebaApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertInitialData(VoluntarioService service, OngService os) {
		return args -> {

			Ong ong1 = new Ong();
			ong1.setContinente("Africa");
			ong1.setNombre("Saharaoui children");
			ong1.setPais("Sahara Occidental");
			ong1.setDescripcion(
					"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
			ong1.setUrlImagen("https://i.imgur.com/lupTqbA.jpg");
			ong1.setPresupuesto(300);
			os.addOng(ong1);

			Ong ong2 = new Ong();
			ong2.setContinente("Africa");
			ong2.setNombre("Contruir pozos");
			ong2.setPais("Malí");
			ong2.setDescripcion(
					"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
			os.addOng(ong2);

			Ong ong3 = new Ong();
			ong3.setContinente("SurAmerica");
			ong3.setNombre("Amazonia limpia");
			ong3.setPais("Brasil");
			ong3.setDescripcion(
					"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
			ong3.setUrlImagen("https://i.imgur.com/DxmMIoG.jpg");
			ong3.setPresupuesto(500);
			os.addOng(ong3);

			Ong ong4 = new Ong();
			ong4.setContinente("Europa");
			ong4.setNombre("Help refugees");
			ong4.setPais("Bélgica");
			ong4.setDescripcion(
					"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
			ong4.setUrlImagen("https://i.imgur.com/sRqRgtm.jpg");
			ong4.setPresupuesto(800);
			os.addOng(ong4);

			Ong ong5 = new Ong();
			ong5.setContinente("Asia");
			ong5.setNombre("Indian children");
			ong5.setPais("India");
			ong5.setDescripcion(
					"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
			ong5.setUrlImagen("https://i.imgur.com/yHHmIBY.jpg");
			ong5.setPresupuesto(300);
			os.addOng(ong5);

			Ong ong6 = new Ong();
			ong6.setContinente("Oceania");
			ong6.setNombre("Liberar a Willy");
			ong6.setPais("Australia");
			ong6.setDescripcion(
					"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.");
			ong6.setUrlImagen("https://i.imgur.com/Yr6E2MM.jpg");
			ong6.setPresupuesto(100);
			os.addOng(ong6);

			Voluntario admin1 = new Voluntario();
			admin1.setMail("admin@admin.com");
			admin1.setNombre("admin");
			admin1.setApellidos("admin");
			admin1.setPassword("admin");
			admin1.setAdmin(true);
			admin1.setFoto("https://i.imgur.com/R08uGwr.jpg");
			admin1.setDonado(-1);
			service.addVoluntario(admin1);

			Voluntario vol1 = new Voluntario();
			vol1.setMail("usuario@usuario.com");
			vol1.setNombre("Usuario");
			vol1.setApellidos("Usuario");
			vol1.setPassword("1234");
			vol1.setAdmin(false);
			vol1.setFoto("https://i.imgur.com/ILNH5A2.jpg");
			vol1.setOng(ong2);
			service.addVoluntario(vol1);

			Voluntario vol2 = new Voluntario();
			vol2.setMail("juangil@usuario.com");
			vol2.setNombre("Juan");
			vol2.setApellidos("Gil");
			vol2.setPassword("1234");
			vol2.setAdmin(false);
			vol2.setFoto("https://i.imgur.com/yNOsAcT.jpg");
			vol2.setDonado(200);
			service.addVoluntario(vol2);

			Voluntario vol3 = new Voluntario();
			vol3.setMail("guillerueda@usuario.com");
			vol3.setNombre("Guillermo");
			vol3.setApellidos("De Rueda");
			vol3.setPassword("1234");
			vol3.setAdmin(false);
			vol3.setFoto("https://i.imgur.com/SEziHmE.jpg");
			vol3.setOng(ong4);
			vol3.setDonado(400);
			service.addVoluntario(vol3);

			Voluntario vol4 = new Voluntario();
			vol4.setMail("santiagoperez@usuario.com");
			vol4.setNombre("Santiago");
			vol4.setApellidos("Pérez");
			vol4.setPassword("1234");
			vol4.setAdmin(false);
			vol4.setFoto("https://i.imgur.com/aC6nYF9.jpg");
			vol4.setOng(ong2);
			service.addVoluntario(vol4);

			Voluntario vol5 = new Voluntario();
			vol5.setMail("cesarsilva@usuario.com");
			vol5.setNombre("César");
			vol5.setApellidos("Silva");
			vol5.setPassword("1234");
			vol5.setAdmin(false);
			vol5.setFoto("https://i.imgur.com/BTntWiV.jpg");
			vol5.setOng(ong1);
			vol5.setDonado(60);
			service.addVoluntario(vol5);

			Voluntario vol6 = new Voluntario();
			vol6.setMail("jorgediaz@usuario.com");
			vol6.setNombre("Jorge");
			vol6.setApellidos("Díaz");
			vol6.setPassword("1234");
			vol6.setAdmin(false);
			vol6.setFoto("https://i.imgur.com/wn0z7rv.jpg");
			vol6.setOng(ong5);
			vol6.setDonado(120);
			service.addVoluntario(vol6);

			Voluntario vol7 = new Voluntario();
			vol7.setMail("carlosgon@usuario.com");
			vol7.setNombre("Carlos");
			vol7.setApellidos("Gonzalez");
			vol7.setPassword("1234");
			vol7.setAdmin(false);
			vol7.setOng(ong6);
			vol7.setDonado(75);
			service.addVoluntario(vol7);

			Voluntario vol8 = new Voluntario();
			vol8.setMail("jaimesomet@usuario.com");
			vol8.setNombre("Jaime");
			vol8.setApellidos("Somet");
			vol8.setPassword("1234");
			vol8.setAdmin(false);
			vol8.setFoto("https://www.merca2.es/wp-content/uploads/2018/04/montoro.jpg");
			vol8.setOng(ong6);
			vol8.setDonado(20);
			service.addVoluntario(vol8);

			Voluntario vol9 = new Voluntario();
			vol9.setMail("elenasanchez@usuario.com");
			vol9.setNombre("Elena");
			vol9.setApellidos("Sanchez");
			vol9.setPassword("1234");
			vol9.setAdmin(false);
			vol9.setFoto("https://i.imgur.com/oWsVTKB.jpg");
			vol9.setOng(ong6);
			vol9.setDonado(70);
			service.addVoluntario(vol9);

			Voluntario vol10 = new Voluntario();
			vol10.setMail("paulaflores@usuario.com");
			vol10.setNombre("Antonio");
			vol10.setApellidos("Perez");
			vol10.setPassword("1234");
			vol10.setFoto("https://i.imgur.com/MLKJUMF.jpg");
			vol10.setAdmin(false);
			vol10.setOng(ong1);
			vol10.setDonado(100);
			service.addVoluntario(vol10);

		};
	}
}