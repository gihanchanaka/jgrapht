package org.jgrapht.experimental.subgraphisomorphism;

import static org.junit.Assert.*;

import java.util.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.experimental.subgraphisomorphism.*;
import org.junit.Test;

public class GraphOrderingTest {

	@Test
	public void testUndirectedGraph() {
		/*
		 *   v1--v2
		 *    |\  |  v5  
		 *    | \ |
		 *   v3  v4
		 *   
		 */
		UndirectedGraph<String, DefaultEdge> g1 =
				new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		String v1 = "v1",
			   v2 = "v2",
			   v3 = "v3",
			   v4 = "v4",
			   v5 = "v5";
		
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addVertex(v5);

		g1.addEdge(v1, v2);
		g1.addEdge(v1, v3);
		g1.addEdge(v1, v4);
		g1.addEdge(v2, v4);
		
		GraphOrdering<String, DefaultEdge> g1Ordering =
				new GraphOrdering<String, DefaultEdge>(g1);
		
		assertEquals(5, g1Ordering.getVertexCount());
		
		
		int v1o = g1Ordering.getVertexOrder(v1),
			v2o = g1Ordering.getVertexOrder(v2),
			v3o = g1Ordering.getVertexOrder(v3),
			v4o = g1Ordering.getVertexOrder(v4),
			v5o = g1Ordering.getVertexOrder(v5);
		
		int[] v1Outs  = {v2o, v3o, v4o};
		int[] v1Outs_ = g1Ordering.getOutEdges(v1o);
		Arrays.sort(v1Outs);
		Arrays.sort(v1Outs_);
		
		int[] v2Outs  = {v1o, v4o};
		int[] v2Outs_ = g1Ordering.getOutEdges(v2o);
		Arrays.sort(v2Outs);
		Arrays.sort(v2Outs_);
		
		int[] v3Outs  = {v1o};
		int[] v3Outs_ = g1Ordering.getOutEdges(v3o);
		Arrays.sort(v3Outs);
		Arrays.sort(v3Outs_);
		
		int[] v4Outs  = {v1o, v2o};
		int[] v4Outs_ = g1Ordering.getOutEdges(v4o);
		Arrays.sort(v4Outs);
		Arrays.sort(v4Outs_);
		
		int[] v5Outs  = {};
		int[] v5Outs_ = g1Ordering.getOutEdges(v5o);
		Arrays.sort(v5Outs);
		Arrays.sort(v5Outs_);
		
		assertArrayEquals(v1Outs, v1Outs_);
		assertArrayEquals(v2Outs, v2Outs_);
		assertArrayEquals(v3Outs, v3Outs_);
		assertArrayEquals(v4Outs, v4Outs_);
		assertArrayEquals(v5Outs, v5Outs_);
		

		int[] v1Ins  = {v2o, v3o, v4o};
		int[] v1Ins_ = g1Ordering.getOutEdges(v1o);
		Arrays.sort(v1Ins);
		Arrays.sort(v1Ins_);
		
		int[] v2Ins  = {v1o, v4o};
		int[] v2Ins_ = g1Ordering.getOutEdges(v2o);
		Arrays.sort(v2Ins);
		Arrays.sort(v2Ins_);
		
		int[] v3Ins  = {v1o};
		int[] v3Ins_ = g1Ordering.getOutEdges(v3o);
		Arrays.sort(v3Ins);
		Arrays.sort(v3Ins_);
		
		int[] v4Ins  = {v1o, v2o};
		int[] v4Ins_ = g1Ordering.getOutEdges(v4o);
		Arrays.sort(v4Ins);
		Arrays.sort(v4Ins_);
		
		int[] v5Ins  = {};
		int[] v5Ins_ = g1Ordering.getOutEdges(v5o);
		Arrays.sort(v5Ins);
		Arrays.sort(v5Ins_);
		
		assertArrayEquals(v1Ins, v1Ins_);
		assertArrayEquals(v2Ins, v2Ins_);
		assertArrayEquals(v3Ins, v3Ins_);
		assertArrayEquals(v4Ins, v4Ins_);
		assertArrayEquals(v5Ins, v5Ins_);
		
		
		assertEquals(false, g1Ordering.hasEdge(v1o, v1o));
		assertEquals(true, g1Ordering.hasEdge(v1o, v2o));
		assertEquals(true, g1Ordering.hasEdge(v1o, v3o));
		assertEquals(true, g1Ordering.hasEdge(v1o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v1o, v5o));
		assertEquals(true, g1Ordering.hasEdge(v2o, v1o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v3o));
		assertEquals(true, g1Ordering.hasEdge(v2o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v5o));
		assertEquals(true, g1Ordering.hasEdge(v3o, v1o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v5o));
		assertEquals(true, g1Ordering.hasEdge(v4o, v1o));
		assertEquals(true, g1Ordering.hasEdge(v4o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v5o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v1o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v5o));
	}

	@Test
	public void testDirectedGraph() {
		/*
		 *   v1 ---> v2 <---> v3 ---> v4     v5
		 *   
		 */
		DirectedGraph<String, DefaultEdge> g1 =
				new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		String v1 = "v1",
			   v2 = "v2",
			   v3 = "v3",
			   v4 = "v4",
			   v5 = "v5";
		
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addVertex(v5);

		g1.addEdge(v1, v2);
		g1.addEdge(v2, v3);
		g1.addEdge(v3, v2);
		g1.addEdge(v3, v4);
		
		
		GraphOrdering<String, DefaultEdge> g1Ordering =
				new GraphOrdering<String, DefaultEdge>(g1);
		
		assertEquals(5, g1Ordering.getVertexCount());
		
		
		int v1o = g1Ordering.getVertexOrder(v1),
			v2o = g1Ordering.getVertexOrder(v2),
			v3o = g1Ordering.getVertexOrder(v3),
			v4o = g1Ordering.getVertexOrder(v4),
			v5o = g1Ordering.getVertexOrder(v5);
		
		int[] v1Outs  = {v2o};
		int[] v1Outs_ = g1Ordering.getOutEdges(v1o);
		Arrays.sort(v1Outs);
		Arrays.sort(v1Outs_);
		
		int[] v2Outs  = {v3o};
		int[] v2Outs_ = g1Ordering.getOutEdges(v2o);
		Arrays.sort(v2Outs);
		Arrays.sort(v2Outs_);
		
		int[] v3Outs  = {v2o, v4o};
		int[] v3Outs_ = g1Ordering.getOutEdges(v3o);
		Arrays.sort(v3Outs);
		Arrays.sort(v3Outs_);
		
		int[] v4Outs  = {};
		int[] v4Outs_ = g1Ordering.getOutEdges(v4o);
		Arrays.sort(v4Outs);
		Arrays.sort(v4Outs_);
		
		int[] v5Outs  = {};
		int[] v5Outs_ = g1Ordering.getOutEdges(v5o);
		Arrays.sort(v5Outs);
		Arrays.sort(v5Outs_);
		
		assertArrayEquals(v1Outs, v1Outs_);
		assertArrayEquals(v2Outs, v2Outs_);
		assertArrayEquals(v3Outs, v3Outs_);
		assertArrayEquals(v4Outs, v4Outs_);
		assertArrayEquals(v5Outs, v5Outs_);
		

		int[] v1Ins  = {};
		int[] v1Ins_ = g1Ordering.getInEdges(v1o);
		Arrays.sort(v1Ins);
		Arrays.sort(v1Ins_);
		
		int[] v2Ins  = {v1o, v3o};
		int[] v2Ins_ = g1Ordering.getInEdges(v2o);
		Arrays.sort(v2Ins);
		Arrays.sort(v2Ins_);
		
		int[] v3Ins  = {v2o};
		int[] v3Ins_ = g1Ordering.getInEdges(v3o);
		Arrays.sort(v3Ins);
		Arrays.sort(v3Ins_);
		
		int[] v4Ins  = {v3o};
		int[] v4Ins_ = g1Ordering.getInEdges(v4o);
		Arrays.sort(v4Ins);
		Arrays.sort(v4Ins_);
		
		int[] v5Ins  = {};
		int[] v5Ins_ = g1Ordering.getInEdges(v5o);
		Arrays.sort(v5Ins);
		Arrays.sort(v5Ins_);
		
		assertArrayEquals(v1Ins, v1Ins_);
		assertArrayEquals(v2Ins, v2Ins_);
		assertArrayEquals(v3Ins, v3Ins_);
		assertArrayEquals(v4Ins, v4Ins_);
		assertArrayEquals(v5Ins, v5Ins_);
		
		
		assertEquals(false, g1Ordering.hasEdge(v1o, v1o));
		assertEquals(true, g1Ordering.hasEdge(v1o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v1o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v1o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v1o, v5o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v1o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v2o));
		assertEquals(true, g1Ordering.hasEdge(v2o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v2o, v5o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v1o));
		assertEquals(true, g1Ordering.hasEdge(v3o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v3o));
		assertEquals(true, g1Ordering.hasEdge(v3o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v3o, v5o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v1o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v4o, v5o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v1o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v2o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v3o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v4o));
		assertEquals(false, g1Ordering.hasEdge(v5o, v5o));
	}
}
