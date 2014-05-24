package framework.impl;

import framework.AgentJoining;
import framework.IActionable;
import framework.IContext;
import framework.Tick;

public class AgentJoining_impl extends AgentJoining {

	@Override
	protected JoiningEntity make_JoiningEntity() {
		return new JoiningEntity() {

			@Override
			protected Tick make_joinTick() {
				return eco_requires().universalTick();//Hello().helloWorld();
			}

			@Override
			protected IContext make_joinContext() {
				return eco_requires().universalContext();
			}

			@Override
			protected IActionable make_joinActionable() {
				return eco_requires().universalActionable();
			}
			
		};
	}

}
