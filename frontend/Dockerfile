FROM node:14-alpine
WORKDIR /src
COPY package.json package-lock.json /src/
ENV NODE_ENV=production
RUN npm install --production
COPY . /src
EXPOSE 3000
CMD npm start