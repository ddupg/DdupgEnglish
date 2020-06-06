package com.ddupg.english.phometic;

import java.util.List;

import lombok.Data;

@Data
public class RawPhoneticSign {

  private int id;

  private String show;

  private String audio;

  private String pic;

  private String video;

  private List<Integer> tags;

}
