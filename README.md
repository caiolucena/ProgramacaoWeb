# ProgramacaoWeb
** Projeto PWEB - Entrega do Sprint 1 **
Equipe: Adalcino Junior, Caio Lucena e Lucas Cosmo


-> Localização dos Artefatos

Projeto de Baixo Nível: ProgramaçãoWeb/Diagrama de Classe.png
Código JAVA do sistema: ProgramaçãoWeb/BiblioWeb/src/main/java
Documentação (JavaDoc): 
Código de testes unitários do projeto (JUnit): ProgramaçãoWeb/BiblioWeb/src/test/java
Processo automático de build do sistema: 

-> Primeiro passo 

** Importe o Projeto no Eclipse. Nome do arquivo:  **

-> Configurações iniciais

** Configurar a conexão no Eclipse **

>> Expanda o Projeto Maven biblioWeb
>> Vá até a pasta src/main/java
>> Expanda o pacote br.uep.dao
>> Abra o arquivo Conexao.java 
>> Configure com as suas credenciais de conexão no MySQL
>> No campo " private static String user = "root"; ", substitua o "root" pelo nome do usuário da credencial
>> No campo "  private static String pass = "root"; ", substitua o "root" pela senha da credencial
>> Pressione Ctrl + S para salvar

-> Para Executar os testes JUnit no Eclipse 

** Verifique se existe a pasta "src/test/resources". Caso não haja, clique com o botão direito em cima da pasta biblioWeb -> New -> Folder e crie a pasta com o nome "src/test/resources".

>> Expanda o Projeto Maven biblioWeb
>> Clique com o botão direito do mouse em cima da pasta com o nome src/test/java
>> Clique em Run As -> JUnit Test
>> Aguarde os testes serem executados

->Executar os Scripts SQL
insert into area (nome) values ('EXATAS');
insert into tema (nome,id) values ('COMPUTACAO',1);
Insert into idade (Codigo, Nome, Uf) values ('2504009','Campina Grande', 'PB');
