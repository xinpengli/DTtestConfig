package com.geekplus.maptest.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * athena测试平台，rpc任务下发接口
 * @author wanghao
 */
@Controller
@RequestMapping("/rpc")
public class RpcTestControllor {

    @RequestMapping("/modify")
    public String modifySystmeConfig(@RequestParam("name") String name, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

      response.getWriter().write( "<script>alert('"+name+"');</script>");
   //   response.getWriter().write( name);
        response.getWriter().flush();


        return "robot";


    }

@RequestMapping("/index")
public String login(){

    return "robot";
}




    @RequestMapping(value="/maptest",method =RequestMethod.POST)
//    @ResponseBody
	//获取前端data传过来的控件name=file的filew文件，且定义为MultipartFile类型的file变量，MultipartFile为获取formdate类型的file类型
    public String testRobot(@RequestParam("file") MultipartFile file,HttpServletResponse response){

        if (!file.isEmpty()) {
            try {

            /*     * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;*/
                  File file1 = new File(
                        "C:", file.getOriginalFilename());
                  if (file1.exists()){
                      System.out.println(file1.getPath());
                      file1.delete();
                  }
                //BufferedOutputStream 写入file.getOriginalFilename()文件里，写文件
                BufferedOutputStream out = new BufferedOutputStream(
//                        new FileOutputStream(new File("/usr/local/geekplus/tomcat-rms/webapps/athena/WEB-INF/classes/config/system/"+file.getOriginalFilename())));
                        new FileOutputStream(file1));


//                        new FileOutputStream(new File("C:\\Users\\lixinpeng"+file.getOriginalFilename())));
                System.out.println(file.getName());
				//file.getBytes(),返回文件内容的二进制数组数据byte[],BufferedOutputStream.write（byte[] b）写入将b.length个二进制数据写入文件流
                out.write(file.getBytes());

                out.flush();
                out.close();
//               String fileString= out.toString();
                response.setContentType("text/html;charset=utf-8");

                response.getWriter().write( "<script>alert('"+ file1.getPath()+"');</script>");
                //   response.getWriter().write( name);
                response.getWriter().flush();
                return  "robot" ;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            /*new File(file.getOriginalFilename());*/


        } else {
            return "上传失败，因为文件是空的.";
        }





    }

}
