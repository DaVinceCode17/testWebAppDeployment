FROM tomcat:10.1-jdk17

# Copy WAR file to Tomcat webapps as ROOT.war
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat (hindi java -jar!)
CMD ["catalina.sh", "run"]
