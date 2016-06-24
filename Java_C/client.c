/*************************************************************************
	> File Name: client.c
	> Author: Wu_Being
	> Mail: 1040003585@qq.com 
	> Created Time: 2016年06月25日 01:30
 	> Description: 
 ************************************************************************/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>
#include<sys/socket.h>

void error_handling(char *message);

int main(int argc, char *argv[])
{
	int server_sock;
	struct sockaddr_in server_addr;

	char message[30];
	int str_len = 0;

	if(3 != argc){
		printf("Usage : %s <IP> <Port> \n", argv[0]);
		exit(1);
	}

	server_sock = socket(PF_INET, SOCK_STREAM, 0);
	if(-1 == server_sock){
		error_handling("socket() error!");
		exit(1);
	}

	memset(&server_addr, 0, sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.s_addr = inet_addr(argv[1]);
	server_addr.sin_port = htons(atoi(argv[2]));

	if( -1 == connect(server_sock, (struct sockaddr*)&server_addr,
					  sizeof(server_addr)) ){
		error_handling("connect() error!");
	}

	printf("C:connect to server success...\n");
	printf("C:input data(with 'end' for exit the programing)\n");

	int goon=1;
	//建立连接之后的处理逻辑
	while(goon){
		printf("C:please input the data :");
		memset(message, 32, sizeof(message));
		//scanf("%s",message);//unsuccess!!!
		fgets(message,30,stdin);//success...

		printf("C:send the data: %s",message);	
		write(server_sock, message, strlen(message));

		str_len = read(server_sock, message, 30-1);
		message[str_len]=0;
		if(-1 == str_len){
			error_handling("read() error!");
		}
		//printf("%d\n",str_len);
		if(strncmp("end",message,3)==0){
			goon=0;
			printf("C:end....\n\n");
		}	
		else{
			printf("C:Message from server : %s \n", message);
		}
	}
	//断开连接，关闭套接字
	close(server_sock);

	return 0;
}

void error_handling(char *message)
{
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}
