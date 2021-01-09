# gradle-localstack-sqs-example
![Build](https://github.com/gregwhitaker/gradle-localstack-sqs-example/workflows/Build/badge.svg)

An example of working with mock AWS SQS queues using [LocalStack](https://github.com/localstack/localstack) and the [Gradle LocalStack Plugin](https://github.com/Nike-Inc/gradle-localstack).

## Building the Example
Run the following command to build the example:

    ./gradlew clean build

## Running the Example
Follow the steps below to run the example application:

1. Run the following command to setup the LocalStack environment:

        ./gradlew startLocalStack

   If successful, you will see that the S3 buckets were created in the console:

         > Task :setupQueue
         Creating SQS queue: example-queue
         Created SQS queue: [example-queue]
         
         > Task :startLocalStack
         LocalStack Started

2. Run the following command to list the S3 buckets in LocalStack:

         ./gradlew listSqsQueues

   If successful, you will see something similar to the following in the console:
   
         > Task :listSqsQueues
         ┌─────────────────────────────────────────────────┬─────────────────────────────────────────────────┬────────────────────────────────────────────────┐
         │QueueName                                        │QueueUrl                                         │ApproximateNumberOfMessages                     │
         ├─────────────────────────────────────────────────┼─────────────────────────────────────────────────┼────────────────────────────────────────────────┤
         │example-queue                                    │http://localhost:4566/000000000000/example-queue │0                                               │
         └─────────────────────────────────────────────────┴─────────────────────────────────────────────────┴────────────────────────────────────────────────┘

3. Run the following command to execute the application:

         ./gradlew run

   If successful, you will see the following in the console:

         > Task :run
         [main] INFO example.SqsExampleApplication - Resolved queue 'example-queue' to url: http://localhost:4566/000000000000/example-queue
         [main] INFO example.SqsExampleApplication - Sending message to 'example-queue': Example message 1
         [main] INFO example.SqsExampleApplication - Sent message: faf5c2a7-5d16-25b4-e794-55bf78dd041d
         [main] INFO example.SqsExampleApplication - Sending message to 'example-queue': Example message 2
         [main] INFO example.SqsExampleApplication - Sent message: f6b12185-e15a-83a2-55e9-248d8ee38e3e
         [main] INFO example.SqsExampleApplication - Sending message to 'example-queue': Example message 3
         [main] INFO example.SqsExampleApplication - Sent message: a3b94f20-d46e-d271-0e78-ec984b6e2724
         [main] INFO example.SqsExampleApplication - Sending message to 'example-queue': Example message 4
         [main] INFO example.SqsExampleApplication - Sent message: e3a1190b-b827-5ea3-ae49-f53ed019d28f
         [main] INFO example.SqsExampleApplication - Sending message to 'example-queue': Example message 5
         [main] INFO example.SqsExampleApplication - Sent message: c81ff129-c327-4dc9-45a3-fbd4561a2a67
         [main] INFO example.SqsExampleApplication - Receiving messages from 'example-queue'...
         [main] INFO example.SqsExampleApplication - Received message: Example message 1
         [main] INFO example.SqsExampleApplication - Receiving messages from 'example-queue'...
         [main] INFO example.SqsExampleApplication - Received message: Example message 2
         [main] INFO example.SqsExampleApplication - Receiving messages from 'example-queue'...
         [main] INFO example.SqsExampleApplication - Received message: Example message 3
         [main] INFO example.SqsExampleApplication - Receiving messages from 'example-queue'...
         [main] INFO example.SqsExampleApplication - Received message: Example message 4
         [main] INFO example.SqsExampleApplication - Receiving messages from 'example-queue'...
         [main] INFO example.SqsExampleApplication - Received message: Example message 5

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/gradle-localstack-sqs-example/issues).

## License
MIT License

Copyright (c) 2021 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
