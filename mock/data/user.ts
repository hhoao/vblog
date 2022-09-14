import { GetUserInfoModel } from '/@/api/model/userModel';

export const accountList = (() => {
  const result: any[] = [];
  for (let index = 0; index < 20; index++) {
    result.push({
      id: `${index}`,
      username: '@first',
      email: '@email',
      phone: '@natural(1000000000, 9999999999)',
      nickname: '@cname()',
      role: {
        id: `${index}`,
        name: '@name',
        description: '@sentence(3, 5',
        'status|1': ['0', '1'],
        createTime: '@time',
      },
      createTime: '@datetime',
      latestTime: '@datetime',
      remark: '@cword(10,20)',
      'status|1': ['0', '1'],
    });
  }
  return result;
})();

export function createFakeUserList(): GetUserInfoModel[] {
  return [
    {
      id: 1,
      username: 'Vben',
      avatar: 'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640',
      password: '123456',
      email: 'test@gmail.com',
      signature: '海纳百川，有容乃大',
      introduction: '微笑着，努力着，欣赏着',
      title: '交互专家',
      notifyCount: 12,
      unreadCount: 11,
      country: 'China',
      address: 'Xiamen City 77',
      phone: '0592-268888888',
      createTime: '2022-09-09T02:19:26.000+00:00',
      tags: [
        {
          id: 0,
          label: '很有想法的',
        },
        {
          id: 1,
          label: '专注设计',
        },
        {
          id: 2,
          label: '辣~',
        },
        {
          id: 3,
          label: '大长腿',
        },
        {
          id: 4,
          label: '川妹子',
        },
        {
          id: 5,
          label: '海纳百川',
        },
      ],
      role: {
        id: 1,
        name: 'ROLE_ADMIN',
        description: '管理者',
        status: true,
        createTime: '2022-09-09T02:19:26.000+00:00',
      },
    },
    {
      id: 2,
      username: 'test',
      avatar: 'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640',
      password: '123456',
      role: {
        id: 2,
        name: 'TESTER',
        description: '测试人员',
        status: true,
        createTime: '2022-09-09T02:19:26.000+00:00',
      },
    },
  ];
}
export const token1 = 'wethasdfkajsdfn';
export const token2 = 'usdfhwkervsdfg';
export const generateToken = (id: number) => {
  switch (id) {
    case 1:
      return token1;
    case 2:
      return token2;
    default:
      return '';
  }
};

export const getUserByToken = (token: string): GetUserInfoModel | undefined => {
  switch (token) {
    case token1:
      return createFakeUserList()[0];
    case token2:
      return createFakeUserList()[1];
    default:
      return undefined;
  }
};
