FROM tomcat:10.1-jdk17

# Create target directory if it doesn't exist
RUN mkdir -p /app/target

# Copy the WAR file (Maven builds to target/)
COPY src/main/webapp/ /usr/local/tomcat/webapps/ROOT/

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
