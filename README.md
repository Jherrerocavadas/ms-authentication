<div align="center">

![Logo](https://placehold.co/730x410)

</div>

<h1 align = "center"> ms-authentication </h1>

# Sobre
Esse projeto foi desenvolvido para autenticar microsserviços quando consumirem ou forem consumidos por outros microsserviços. Dentre suas funcionalidades, estão:

- Geração de token JWT para o serviço cadastrado.
- Checagem da validade e autenticidade do token JWT informado.

Observação: Nem todos os recursos podem estar implementados e/ou funcionando corretamente. Para verificar quais são as funcionalidades implementadas consulte a guia "[Funcionalidades](#Funcionalidades)", mais abaixo.

# Índice

* [Sobre](#sobre)
* [Índice](#índice)
* [Funcionalidades](#funcionalidades)
* [Estrutura do projeto](#estrutura-do-projeto)
* [Run Configurations](#run-configurations)
  * [Template 'Application'](#template-application)
  * [Template 'Maven'](#template-maven)
* [Demonstração](#demonstração)
* [Pré Requisitos](#pré-requisitos)
  * [Software](#software)
* [Tecnologias](#tecnologias)
* [Autor](#autor)
  * [Johann Herrero Cavadas](#johann-herrero-cavadas)


# Funcionalidades

- [x] Geração de token JWT para o serviço cadastrado.
- [x] Checagem da validade e autenticidade do token JWT informado.

# Estrutura do projeto
```bash
├───.idea                                               
│   └───runConfigurations                               
├───.mvn                                                
│   └───wrapper                                         
├───src                                                 
    └───main                                            
        ├───java                                        
        │   └───br                                      
        │       └───com                                 
        │           └───jherrerocavadas                 
        │               └───msauthentication            
        │                   ├───config                  
        │                   ├───controller              
        │                   ├───dto                     
        │                   │   ├───request             
        │                   │   └───response            
        │                   ├───entity
        │                   ├───exception
        │                   │   ├───dto
        │                   │   └───throwable
        │                   ├───repository
        │                   ├───service
        │                   └───util
        └───resources

```


# Run Configurations

Há duas opções de configurações de execução do projeto pelo Intellij: 
A configuração utilizando o template do Maven e a configuração utilizando o template Application.
Ambas permitem executar o projeto com diferentes profiles, mas apenas o template Application permite
executar a aplicação em modo debug no spring-boot 3.x.x.

## Template 'Application'
Copie o trecho abaixo e cole em um arquivo .xml na pasta **.idea\runConfigurations**, substituindo 
os seguintes campos:

**${PROFILE}**: substituir pelo profile que deseja executar a aplicação. no momento, existem 4 **application.properties**
definidos, sendo para os seguintes profiles: **dev**, **devh2**(utilizando o banco em memória H2), **hmg** e **prod**;

```xml
<component name="ProjectRunConfigurationManager">
<configuration default="false" name="MsAuthenticationApplication" type="Application" factoryName="Application" nameIsGenerated="true">
<option name="MAIN_CLASS_NAME" value="br.com.jherrerocavadas.msauthentication.MsAuthenticationApplication" />
<module name="ms-authentication" />
<option name="VM_PARAMETERS" value="-Dspring.profiles.active=${PROFILE}" />
<extension name="coverage">
<pattern>
<option name="PATTERN" value="br.com.jherrerocavadas.msauthentication.entity.*" />
<option name="ENABLED" value="true" />
</pattern>
</extension>
<method v="2">
<option name="Maven.BeforeRunTask" enabled="true" file="$PROJECT_DIR$/pom.xml" goal="compile" />
</method>
</configuration>
</component>
```

## Template 'Maven'
Copie o trecho abaixo e cole em um arquivo .xml na pasta **.idea\runConfigurations**, substituindo
os seguintes campos:

**${PROFILE}**: substituir pelo profile que deseja executar a aplicação. no momento, existem 4 **application.properties**
definidos, sendo para os seguintes profiles: **dev**, **devh2**(utilizando o banco em memória H2), **hmg** e **prod**;

```xml
<component name="ProjectRunConfigurationManager">
  <configuration default="false" name="msauthentication [spring-boot:run...]" type="MavenRunConfiguration" factoryName="Maven">
    <MavenSettings>
      <option name="myGeneralSettings" />
      <option name="myRunnerSettings" />
      <option name="myRunnerParameters">
        <MavenRunnerParameters>
          <option name="cmdOptions" />
          <option name="profiles">
            <set />
          </option>
          <option name="goals">
            <list>
              <option value="spring-boot:run" />
              <option value="-Dspring-boot.run.profiles=${PROFILE}" />
              <option value="-Dpackaging=jar" />
              <option value="-Dspring-boot.run.fork=false" />
            </list>
          </option>
          <option name="pomFileName" value="pom.xml" />
          <option name="profilesMap">
            <map />
          </option>
          <option name="projectsCmdOptionValues">
            <list />
          </option>
          <option name="resolveToWorkspace" value="false" />
          <option name="workingDirPath" value="$PROJECT_DIR$" />
        </MavenRunnerParameters>
      </option>
    </MavenSettings>
    <method v="2" />
  </configuration>
</component>
```

# Demonstração
Execute o projeto, e acesse o Swagger no seguinte link: http://localhost:8083/ms-authentication/swagger-ui/index.html#/.
Aqui você poderá executar um teste aos endpoints, e verificar quais são para teste em outras ferramentas, como o Postman.


# Pré Requisitos

#### Software
- [Java 17 ou superior](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Maven 3.9.5 ou superior](https://maven.apache.org/download.cgi)

# Tecnologias
- [IntelliJ IDEA Community Edition 2023.1.4](https://www.jetbrains.com)
- [Java 17 - Amazon Correto 17.0.7](https://www.java.com/pt-BR/)
- [Maven (Usando o Wrapper do IntelliJ)](https://maven.apache.org)


# Autor
### Johann Herrero Cavadas
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jherrerocavadas/)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:jherrerocavadas@gmail.com)
