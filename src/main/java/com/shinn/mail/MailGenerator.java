package com.shinn.mail;

import java.util.List;


public interface MailGenerator {

  /**
   * build email model
   * 
   * @param target
   * @return
   */
  List<MailModel> generates(Object target);
}
