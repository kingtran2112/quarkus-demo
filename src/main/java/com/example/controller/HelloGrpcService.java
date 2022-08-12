package com.example.controller;

import com.example.Product;
import com.kawaii.service.proto.product.HelloGrpcGrpc;
import com.kawaii.service.proto.product.HelloReply;
import com.kawaii.service.proto.product.HelloRequest;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@GrpcService
public class HelloGrpcService extends HelloGrpcGrpc.HelloGrpcImplBase {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        Product product = new Product();
        product.setName("Tes1t product");
        product.setSKU("0912098092");
        em.persist(product);

        responseObserver.onNext(com.kawaii.service.proto.product.HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
        responseObserver.onCompleted();
    }

}
