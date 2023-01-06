## topic 생성
docker-compose exec kafka kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1

## 생성된 토픽 확인
docker-compose exec kafka kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092

## 컨슈머 실행 하기 (컨슈머는 메세지를 컴슘하기 위해 대기하고 있어야)
docker-compose exec kafka bash
kafka-console-consumer.sh --topic my-topic --bootstrap-server localhost:9092

docker-compose exec kafka bash
kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092


