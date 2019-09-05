FROM nginx:1.17.3-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories && \
      apk add openjdk8-jre-base && \
      rm -rf /var/cache/apk/*

COPY docker/test-resource/*  /usr/share/nginx/html
COPY docker/default.conf /etc/nginx/conf.d/default.conf

ADD target/static-sidecar-1.0.0-SNAPSHOT.jar /app.jar

ENTRYPOINT sh -c 'nginx && java -jar /app.jar'