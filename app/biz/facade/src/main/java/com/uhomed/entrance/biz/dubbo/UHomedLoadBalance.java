//lpackage com.uhomed.entrance.biz.dubbo;
//
//import com.alibaba.dubbo.common.URL;
//import com.alibaba.dubbo.rpc.Invocation;
//import com.alibaba.dubbo.rpc.Invoker;
//import com.alibaba.dubbo.rpc.RpcContext;
//import com.alibaba.dubbo.rpc.RpcException;
//import com.alibaba.dubbo.rpc.cluster.LoadBalance;
//import com.alibaba.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
//
//import java.util.List;
//
///**
// * @author
// * @version $$Id: , v 0.1    Exp $$
// */
//public class UHomedLoadBalance implements LoadBalance {
//    @Override
//    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
//
//        System.out.println(invocation.getAttachments().toString());
//        System.out.println(RpcContext.getContext().get("router"));
//
//
//        System.out.println(invokers);
//        System.out.println(url);
//        System.out.println(invocation);
//        return invokers.get(0);
//    }
//}
