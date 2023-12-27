FROM openjdk:8-jre-alpine

# Установим рабочий каталог
WORKDIR /HW-JavaCore

# Скопируем содержимое текущего каталога в контейнер по адресу /HW-JavaCore
COPY . /HW-JavaCore

# Скомпилируем Java-код
RUN javac -d out src/main/java/com/example/Main.java

# Запустим приложение при запуске контейнера
CMD ["java", "-cp", "out", "Main"]