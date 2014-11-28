package ann.jn.teach.gen;

import ann.jn.neuroNet.NeuralNet;
import ann.jn.neuroNet.Neuron;

public final class WeightMapUtils {
	public static final void setWeights(NeuralNet net, WeightMap weightMap) {
		if (net.getNumLayers() != weightMap.getNumLayers()) {
			throw new IllegalArgumentException("NeuralNet and Weightmap must have same number of layers");
		}
		
		for (int x = 0; x < net.getNumLayers(); x++) {
			Neuron[] layer = net.getLayer(x);
			float[][] weights = weightMap.getLayer(x);
			
			for (int y = 0; y < layer.length; y++) {
				layer[y].setWeights(weights[y]);
			}
		}
	}
	
	public static final WeightMap getWeights(NeuralNet net) {
		int[] lengths = new int[net.getNumLayers() + 1];
		lengths[0] = net.getInputs().length;
		for (int i = 1; i < net.getNumLayers(); i++) {
			lengths[i] = net.getLayer(i).length;
		}
		
		WeightMap map = new WeightMap(false, lengths);
		
		for (int x = 0; x < net.getNumLayers(); x++) {
			Neuron[] layer = net.getLayer(x);
			for (int y = 0; y < layer.length; y++) {
				map.setWeightsForNeuron(x, y, layer[y].getWeights());
			}
		}
		
		return map;
	}
	
	/**
	 * Returns a new {@link NeuralNet} with the same number of layers and the same
	 * layer lengths as the template net given. The values of the Weights in the
	 * {@link Neurons} in the template net are not copied.
	 * @param template
	 * @return
	 */
	public static final NeuralNet genMatchingNet(NeuralNet template) {
		int[] layers = new int[template.getNumLayers()];
		for (int i = 0; i < template.getNumLayers(); i++) {
			layers[i] = template.getLayer(i).length;
		}
		return new NeuralNet(template.getActivationFunction(), layers);
	}
}
