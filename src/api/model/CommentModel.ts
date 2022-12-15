import { BasicPageParams } from '/@/api/model/baseModel';

export interface CommentModel {
  id: number;
  author: string;
  content: string;
  lastModification: Date;
}
export interface CommentParams {
  id?: number;
  author?: string;
}

export type CommentPageParams = CommentParams & BasicPageParams;
