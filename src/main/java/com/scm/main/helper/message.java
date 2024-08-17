package com.scm.main.helper;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class message {
	private String content;
	@Builder.Default
	private messagetype type= messagetype.primary;
	
}
