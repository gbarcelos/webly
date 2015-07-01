Webly
=====

Projeto exemplo de um blog feito em Java, Apache Maven, Spring Framework e Apache Wicket 1.5.4.

Funcionalidades
---------------

* Página inicial;
	* 5 últimos posts;
	* paginação;
* Quem somos;
* Fale Conosco;
	* Captcha;
	* Envio de e-mails;
* Login;
* Criar Conta;
* Confirmar cadastro de usuário;
* Informações sobre a conta do usuário logado
	* Alteração de informações do usuário e informações pessoais;
* Administração de Usuários
	* Pesquisa de usuários;
	* Alteração do perfil do usuário;
	* Alteração do status do usuário: Ativo ou Inativo;
* Administração de Posts
	* Pesquisa de posts;
	* Inclusão/Alteração de posts;
		* Utilização de editor texto;
* Projeto preparado para multiplos locales;

Tecnologias utilizadas na implementação
---------------------------------------

* Java 6.0 (Java SDK 1.6);
* Tomcat v6.0;
* Eclipse Luna;
* Apache Maven 3.2.3;
* PostgreSQL Server 9.1;
* JPA/Hibernate 3;
* Spring Framework 3.0.6;
	* Injeção de dependências e inversão de controle;
	* Integração do Spring com JPA e Hibernate;
	* Spring Security;

* Apache Wicket 1.5.4;

Como o projeto utiliza JPA, o banco de dados torna-se independente da aplicação, porém o desenvolvimento e testes foram feitos com o PostgreSQL.

SEO
---

* Navegação
	* Links de navegação no topo;
	* Links de navegação no  no footer;
	* Links de navegação no  na sidebar;
* URL amigável;
* Páginas
	* Títulos de páginas utilizando title tags;
	* Alt e Titles das imagens;

Futuro
------

* Administração de Posts
	* Permitir inativar/ativar um post;
	* Post inserido com status "Em Edição";
	* Permitir alterar status do post para "Publicado";
* Logs de atividades;
	* Atividades por usuário;
	* Log de entrada saída de usuários;
* Integração com redes sociais;
* Administração de TAG's;
* Administração de Categorias;
* Administração de Midias (imagens, arquivos, etc);
* Administração de Link's;
* Administração de configurações pessoais;
* Redefinir senha do usuário;
* Comentários nos posts;
* RSS;
* SEO
	* Mobile-friendly;
	* Navegação
		* breadcrumbs;
		* SiteMap
			* dois mapas do site (sitemap): um para utilizadores (sitemap) e outro para motores de busca (XML Sitemap);
	* Páginas
		* Meta Tag descritiva;