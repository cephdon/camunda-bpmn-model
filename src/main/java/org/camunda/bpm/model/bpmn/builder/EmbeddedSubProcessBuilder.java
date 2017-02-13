/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.model.bpmn.builder;

import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;

/**
 * @author Sebastian Menski
 */
public class EmbeddedSubProcessBuilder extends AbstractEmbeddedSubProcessBuilder<EmbeddedSubProcessBuilder, AbstractSubProcessBuilder<?>> {

  @SuppressWarnings("rawtypes")
  protected EmbeddedSubProcessBuilder(AbstractSubProcessBuilder subProcessBuilder) {
    super(subProcessBuilder, EmbeddedSubProcessBuilder.class);
  }

  public StartEventBuilder startEvent() {
    return startEvent(null);
  }

  public StartEventBuilder startEvent(String id) {
    StartEvent start = subProcessBuilder.createChild(StartEvent.class, id);
    BpmnShape bpmnShape = subProcessBuilder.createBpmnShape(start);
    BpmnShape subProcessShape = subProcessBuilder.findBpmnShape(subProcessBuilder.element);
    if (subProcessShape != null) {
      Bounds elemBounds = subProcessShape.getBounds();
      Bounds startBounds = bpmnShape.getBounds();
      startBounds.setX(elemBounds.getX() + subProcessBuilder.SPACE);
      startBounds.setY(elemBounds.getY() + elemBounds.getHeight() / 2 - startBounds.getHeight() / 2);
    }
    return start.builder();
  }
}
