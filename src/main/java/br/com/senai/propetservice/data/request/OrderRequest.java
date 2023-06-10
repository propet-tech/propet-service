package br.com.senai.propetservice.data.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderRequest implements Serializable {

  final Long petId;
  final Long serviceId;
  final String notes;
}
